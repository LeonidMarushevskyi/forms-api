package gov.ca.cwds.forms.persistence.dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.forms.inject.FormsSessionFactory;
import gov.ca.cwds.forms.persistence.model.FormSchema;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsSchemasDao extends BaseDaoImpl<FormSchema> {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(FormsSchemasDao.class);

  @Inject
  public FormsSchemasDao(@FormsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<FormSchema> findAll() {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<FormSchema> query =
        session.createNamedQuery(FormSchema.NAMED_QUERY_FIND_ALL, FormSchema.class);
    ImmutableList.Builder<FormSchema> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  public FormSchema findByNameAndVersion(String formName, String schemaVersion) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<FormSchema> query =
        session.createNamedQuery(FormSchema.NAMED_QUERY_FIND_BY_NAME_AND_VERSION, FormSchema.class);
    query.setParameter("formName", formName);
    query.setParameter("schemaVersion", schemaVersion);
    try {
      return query.getSingleResult();
    } catch (NoResultException nre) {
      LOGGER.warn(
          "No schema found for formName: {} and schemaVersion: {}", formName, schemaVersion);
    }
    return null;
  }
}
