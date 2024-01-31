package com.example.productorderservice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.CaseFormat;

/**
 * entity manager에서 모든 엔티티를 가져와서,
 * 엔티티를 stream을 돌면서, @Entity, @Table 어노테이션이 있는지 확인해서 해당 테이블의 이름을 리스트에 담고
 * 모든 테이블의 데이터 삭제.
 */
@Component
public class DatabaseCleanup implements InitializingBean {
	@PersistenceContext
	private EntityManager entityManager;

	private List<String> tableNames;

	@Override
	public void afterPropertiesSet() {
		final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		tableNames = entities.stream()
			.filter(e -> isEntity(e) && hasTableAnnotation(e))
			.map(e -> {
				String tableName = e.getJavaType().getAnnotation(Table.class).name();
				return tableName.isBlank() ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()) :
					tableName;
			})
			.collect(Collectors.toList());

		final List<String> entityNames = entities.stream()
			.filter(e -> isEntity(e) && !hasTableAnnotation(e))
			.map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
			.toList();

		tableNames.addAll(entityNames);
	}

	private boolean isEntity(final EntityType<?> e) {
		return null != e.getJavaType().getAnnotation(Entity.class);
	}

	private boolean hasTableAnnotation(final EntityType<?> e) {
		return null != e.getJavaType().getAnnotation(Table.class);
	}

	@Transactional
	public void execute() {
		entityManager.flush();
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

		for (final String tableName : tableNames) {
			entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
			entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1")
				.executeUpdate();
		}

		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
	}

}
