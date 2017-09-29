package gov.ca.cwds.forms.persistence.dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.forms.inject.FormsSessionFactory;
import gov.ca.cwds.forms.persistence.model.FormInstance;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsDao extends BaseDaoImpl<FormInstance> {

  @Inject
  public FormsDao(@FormsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<FormInstance> findAll() {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<FormInstance> query =
        session.createNamedQuery(FormInstance.NAMED_QUERY_FIND_ALL, FormInstance.class);
    ImmutableList.Builder<FormInstance> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

}
