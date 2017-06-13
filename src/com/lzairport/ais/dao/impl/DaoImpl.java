package com.lzairport.ais.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.AggregationColumnField;
import com.lzairport.ais.models.statistics.ColumnField;
import com.lzairport.ais.models.statistics.GroupField;
import com.lzairport.ais.utils.ExpresstionUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 通用的Dao层接口抽象类
 * 实现各种通用的操作数据库的方法
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Stateless
@SuppressWarnings("unchecked")
public abstract class DaoImpl<K,E> implements IDao<K, E> {
	


	protected EntityManager em;
	
	protected Class<E> clazz;
	
	/**
	 * 将实体对象的ManytoOne的对象以LeftJion的方式链接
	 * @param cb     CriteriaBuilder对象
	 * @param fetchParent Fetch的父类对象
	 * @param javaType 需要链接实体对象的类型
	 */
	
	@SuppressWarnings("rawtypes")
	private void fetchJionEntity(CriteriaBuilder cb,FetchParent fetchParent,Class javaType,QueryConditions conditions,boolean isRoot){
		CriteriaQuery cq = cb.createQuery(javaType);
		Root  root=  cq.from(javaType);
		//获取元模型及取得元模型所对应的所有属性
		EntityType entityType = root.getModel();
		Set<Attribute> attributes = entityType.getAttributes();
		 for (Attribute attribute:attributes){
			 if (attribute instanceof SingularAttribute) {
					SingularAttribute singularAttribute = (SingularAttribute) attribute;
					if ((attribute.getPersistentAttributeType().equals(Attribute.PersistentAttributeType.MANY_TO_ONE))
						&&(!isRoot ||conditions.getFetchManyToOne().equals("ALL")
								||conditions.getFetchManyToOne().indexOf(singularAttribute.getName())!=-1))
						
					{
						//如果该属性是ManytoOne，以LeftJion方式链接
						FetchParent fetchChild = fetchParent.fetch(singularAttribute,JoinType.LEFT);
						//以递归方式链接该属性中包含的ManytoOne的属性
						fetchJionEntity(cb, fetchChild, singularAttribute.getJavaType(),conditions,false);
					}
			 } else if (attribute instanceof SetAttribute){
				 SetAttribute setAttribute = (SetAttribute) attribute;
				 if ((conditions.getFetchOneToMany().indexOf(setAttribute.getName())!= -1||conditions.getFetchOneToMany().equals("ALL")) &&
					 (attribute.getPersistentAttributeType().equals(Attribute.PersistentAttributeType.ONE_TO_MANY)))
				 {
					//如果该属性是OneToMany，以LeftJion方式链接
					 fetchParent.fetch(setAttribute,JoinType.LEFT);
					 
				 }
			 }
		 }
		
	}	
	
	
	
	
	
	
	/**
	 * 以给定的条件查询返回TypedQuery的对象，提供给其他方法以获取最后的结果
	 * @param conditions 给定的条件 
	 * @return TypedQuery对象
	 */
	
	private TypedQuery<E> findByCondition(QueryConditions conditions){
		
		
		List<Order> orders = new ArrayList<Order>();
		if (conditions.getFetchManyToOne() == null){
			conditions.setFetchManyToOne("ALL");
		}
		
		if (conditions.getFetchOneToMany() == null){
			conditions.setFetchOneToMany("");
		}
		
		CriteriaBuilder  cb = em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(clazz);
		Root<E> root=  cq.from(clazz);
		

		EntityType<E> entityType = root.getModel();
		Set<Attribute<? super E, ?>> attributes=entityType.getAttributes();
		for(Attribute<? super E, ?> attribute:attributes){
		//用元模型的属性替换条件表达式中的实体属性的静态字符串
			for (int i=0;(conditions.getExpresstion() != null)&&(i<conditions.getExpresstion().length);i++){
				if (attribute.getName().equals(conditions.getExpresstion()[i])){
					conditions.getExpresstion()[i] = root.get((SingularAttribute<? super E, ?>) attribute);
				}
			}
			
			//用元模型属性替换排序表达式中的实体属性的静态字符串
			for (int i=0;(conditions.getOrders() != null)&&(i< conditions.getOrders().length);i++){

				if (attribute.getName().equals(conditions.getOrders()[i].getName())){
					if (conditions.getOrders()[i].getSortMode().equals(SYS_VARS.AscSORT)) {
						orders.add(cb.asc(root.get((SingularAttribute<? super E, ?>) attribute)));
					}else{
						orders.add(cb.desc(root.get((SingularAttribute<? super E, ?>) attribute)));
					}

				}

				
			}

		}
		
        
		fetchJionEntity(cb, root, clazz,conditions,true);

		
		if (conditions.getExpresstion() != null){
			try {
				Predicate condition = new ExpresstionUtil(cb).composeExpression(conditions.getExpresstion());
				cq.where(condition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		cq.orderBy(orders);
		cq.distinct(true);
		return em.createQuery(cq);
	}
	

	/**
	 * 
	 * @Description: 返回符合条件的记录数量
	 * @param conditions 条件
	 * @return 符合的实体数量
	 */
	private int CountByCondition(QueryConditions conditions){
		
		
		CriteriaBuilder  cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<E> root=  cq.from(clazz);
		EntityType<E> entityType = root.getModel();
		Set<Attribute<? super E, ?>> attributes=entityType.getAttributes();
		for(Attribute<? super E, ?> attribute:attributes){
		//用元模型的属性替换条件表达式中的实体属性的静态字符串
			for (int i=0;(conditions.getExpresstion() != null)&&(i<conditions.getExpresstion().length);i++){
				if (attribute.getName().equals(conditions.getExpresstion()[i])){
					conditions.getExpresstion()[i] = root.get((SingularAttribute<? super E, ?>) attribute);
				}
			}
			

		}
		
		if (conditions.getExpresstion() != null){
			try {
				Predicate condition = new ExpresstionUtil(cb).composeExpression(conditions.getExpresstion());
				cq.where(condition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		cq.select(cb.countDistinct(root));
		return  em.createQuery(cq).getSingleResult().intValue();
		
	}
	

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param cb
	 * @param javaType
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Expression getsubExpression(CriteriaBuilder cb,Class javaType,Root root,Object attributeName,SetAttribute setAttribute,List<Join> joins){

		try {
			CriteriaQuery cq = cb.createQuery(javaType);
			Root subroot;			
			subroot = cq.from(javaType);
			boolean foundJoin =false;
			Join setJoin = null;
			//获取元模型及取得元模型所对应的所有属性
			EntityType entityType = subroot.getModel();
			Set<Attribute> attributes = entityType.getAttributes();
			for(Attribute attribute:attributes){
				if (attribute.getName().equals(attributeName)){
					for (Join join:joins){
						if (javaType.equals(join.getModel().getBindableJavaType())){
							foundJoin = true;
							setJoin = join;
						}
					}
					if (!foundJoin){ 
						setJoin =root.join(setAttribute,JoinType.INNER);
						joins.add(setJoin);
					}
					return setJoin.get((SingularAttribute) attribute);
				}
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			/**
			 *  如果类型不是实体类，返回空
			 */
			return null;
		}

		return null;
		
	}
	
	
	
	/**
	 * 
	 * @Description: 根据条件进行求和处理
	 * @param conditions 条件
	 * @return 最终查询的TypedQuery的对象
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	private TypedQuery<Tuple> AggregationByCondition(QueryConditions conditions) throws Exception{
		
		List<Order> orders = new ArrayList<Order>();
		CriteriaBuilder  cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<E> root=  cq.from(clazz);
		EntityType entityType = root.getModel();
		ExpresstionUtil expresstionUtil = new ExpresstionUtil(cb);
		List<Join> joins = new ArrayList<Join>();
		
		Set<Attribute> attributes=entityType.getAttributes();
		for(Attribute attribute:attributes){
		//用元模型的属性替换条件表达式中的实体属性的静态字符串
			for (int i=0;(conditions.getExpresstion() != null)&&(i<conditions.getExpresstion().length);i++){
				if (attribute.getName().equals(conditions.getExpresstion()[i])){
					conditions.getExpresstion()[i] = root.get((SingularAttribute) attribute);
				}else if (attribute instanceof SetAttribute){
					Class javaType = ((SetAttribute)attribute).getBindableJavaType();
					Expression expression = getsubExpression(cb, javaType,root,conditions.getExpresstion()[i],(SetAttribute) attribute,joins);
					if (expression != null){
						conditions.getExpresstion()[i] = expression;
					}					
				}
			}
			
			
			//用元模型的属性替代求和字段的静态字符串
			for (AggregationColumnField field:conditions.getAggregationFields()){
				for(int i=0;(field.getExpresstion() != null)&&(i<field.getExpresstion().length);i++){
					if (attribute.getName().equals(field.getExpresstion()[i])){
						field.getExpresstion()[i] = root.get((SingularAttribute) attribute);
					}else if (attribute instanceof SetAttribute){
						Class javaType = ((SetAttribute)attribute).getBindableJavaType();
						Expression expression = getsubExpression(cb, javaType,root,field.getExpresstion()[i],(SetAttribute) attribute,joins);
						if (expression != null){
							field.getExpresstion()[i] = expression;
						}
					}
				}
			
				
			for(int i=0;(field.getWhenCondition() != null)&&(i<field.getWhenCondition().length);i++){
					if (attribute.getName().equals(field.getWhenCondition()[i])){
						field.getWhenCondition()[i] = root.get((SingularAttribute) attribute);
					}else if (attribute instanceof SetAttribute){
						Class javaType = ((SetAttribute)attribute).getBindableJavaType();
						Expression expression = getsubExpression(cb, javaType,root,field.getWhenCondition()[i],(SetAttribute) attribute,joins);
						if (expression != null){
							field.getWhenCondition()[i] = expression;
						}
					}
				}
			}
			//用元模型属性替代分组字段的静态字符串
			if (conditions.getGroupFields() != null){
				for (GroupField field:conditions.getGroupFields()){
					if (attribute.getName().equals(field.getExpresstion())){

						field.setExpresstion( root.get((SingularAttribute) attribute));
					}else if (attribute instanceof SetAttribute){
						Class javaType = ((SetAttribute)attribute).getBindableJavaType();
						Expression expression = getsubExpression(cb, javaType,root,field.getExpresstion(),(SetAttribute) attribute,joins);
						if (expression != null){
							field.setExpresstion(expression);
						}						
					}
				}
			}
			
			for (int i=0;(conditions.getOrders() != null)&&(i< conditions.getOrders().length);i++){

				if (attribute.getName().equals(conditions.getOrders()[i].getName())){
					if (conditions.getOrders()[i].getSortMode().equals(SYS_VARS.AscSORT)) {
						orders.add(cb.asc(root.get((SingularAttribute<? super E, ?>) attribute)));
					}else{
						orders.add(cb.desc(root.get((SingularAttribute<? super E, ?>) attribute)));
					}

				}
			}
			

		}
		
		
		
		if (conditions.getExpresstion() != null){
			try {
				Predicate condition = expresstionUtil.composeExpression(conditions.getExpresstion());
				cq.where(condition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		
		for (AggregationColumnField field:conditions.getAggregationFields()){
			
			Expression expression = expresstionUtil.composeExpression(field.getExpresstion());
			
			if (field.getWhenCondition() != null){
				Predicate condition = expresstionUtil.composeExpression(field.getWhenCondition());
				expression = cb.selectCase().when(condition, expression).otherwise(0);
			}
			
			switch (field.getAggregationType()) {
			case Sum:
				selections.add(cb.sum(expression).alias(field.getAlias()));
				break;
			case Avg:
				selections.add(cb.avg(expression).alias(field.getAlias()));
				break;
			case Count:
				selections.add(cb.count(expression).alias(field.getAlias()));
				break;
			case None:
				selections.add(expression.alias(field.getAlias()));
				break;
			}
		}
		
		
		
		List<Expression<?>>  expressions = new ArrayList<Expression<?>>() ;
		if (conditions.getGroupFields() != null){
			for (GroupField field:conditions.getGroupFields()){

				Expression expression=  (Expression) field.getExpresstion();
			
				if (field.getGrpDate() != null){
					expression = expresstionUtil.getYMDExpression(field.getGrpDate(), expression);
				}
				expressions.add(expression);
				selections.add(expression.alias(field.getAlias()));
			}
		}
		//分组字段放入cq中
		cq.groupBy(expressions);
		cq.orderBy(orders);
		
		
		cq.multiselect(selections);
		return em.createQuery(cq);
		
	}
		
	
	

	/** 
	* 通过构造方法指定DAO的具体实现类 
	*/

	public DaoImpl() {
	     
		 ParameterizedType  type  = (ParameterizedType)this.getClass().getGenericSuperclass();
		 clazz = (Class<E>) type.getActualTypeArguments()[1];
	}
	

	/**
	 * 传递EntityManager值给操作em
	 */	
	public abstract void setEntityManager();

	@Override
	public E save(Object entity) {
		entity = em.merge(entity);
		em.persist(entity);
		return (E) entity;
	
	}


	@Override
	public void delete(Object entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	@Override
	public E update(Object entity) {
		return (E) em.merge(entity);
	}

	@Override
	public E findById(Object id) {
		// TODO Auto-generated method stub
		
		return em.find(clazz, id);
		
	}

	@Override
	public List<E> getAll() {
		// TODO Auto-generated method stub
		QueryConditions conditions = new QueryConditions();
		return findByCondition(conditions).getResultList();
	}



	@Override
	public Class<E> getModelClass() {
		// TODO Auto-generated method stub
		return clazz;
	}


	@Override
	public E findByName(Object[] expresstion) {
		return null;
		
	}



	@Override
	public List<E> findByFieldAll(String field, Object value) {
		
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{field,"=",value});
		return findByCondition(conditions).getResultList();
	}

	@Override
	public E findByFieldSingle(String field, Object value) {
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{field,"=",value});
		try {
			return findByCondition(conditions).setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}


	@Override
	public E findByConditionSingle(QueryConditions conditions) {

		try {
			return findByCondition(conditions).setMaxResults(1).getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public List<E> findByConditionAll(QueryConditions conditions) {
		if (conditions.getMax() != 0){
			return  findByCondition(conditions).
					setMaxResults(conditions.getMax()).
					setFirstResult(conditions.getFirst()).getResultList();
		}else {
			return findByCondition(conditions).getResultList();
		}
		
	}



	@Override
	public int findCountByCondition(QueryConditions conditions) {
		
		
		return CountByCondition(conditions);
	}
	
	@Override
	public List<Map<String,Object>> findAggregationByCondition(QueryConditions conditions) throws Exception{
		List<Tuple> tuples = AggregationByCondition(conditions).getResultList();
		List<Map<String,Object>> records = new ArrayList<Map<String,Object>>();
		Map<String, Object> record = null;
		for (Integer i=0; i < tuples.size();i++){
			
			record = new HashMap<String, Object>();
			record.put("id", i);
			for(ColumnField field:conditions.getAggregationFields()){
				record.put(field.getAlias(),tuples.get(i).get(field.getAlias()));
			}
			if (conditions.getGroupFields()!=null){
				for(GroupField field:conditions.getGroupFields()){
					
					if (tuples.get(i).get(field.getAlias()) != null){
						record.put(field.getAlias(),tuples.get(i).get(field.getAlias()).toString());	
					}else{
						record.put(field.getAlias(),null);	
					}
					
					
				}
			}
			if (conditions.getGroupFields() != null&&conditions.getGroupFields().size()>0){
				GroupField field = conditions.getGroupFields().get(0);
				Object rowValue = tuples.get(i).get(field.getAlias());
				if (rowValue != null&&!"".equals(rowValue)){
					records.add(record);
				}
			}else{
				records.add(record);
			}
		}
		return records;
	}
	
	
}
