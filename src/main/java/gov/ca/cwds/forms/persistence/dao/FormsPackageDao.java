package gov.ca.cwds.forms.persistence.dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.forms.inject.FormsSessionFactory;
import gov.ca.cwds.forms.persistence.model.FormsPackage;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author CWDS TPT-2 Team
 */
public class FormsPackageDao extends BaseDaoImpl<FormsPackage> {

  @Inject
  public FormsPackageDao(@FormsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<FormsPackage> findAll() {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<FormsPackage> query =
        session.createNamedQuery(FormsPackage.NAMED_QUERY_FIND_ALL, FormsPackage.class);
    ImmutableList.Builder<FormsPackage> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  public List<FormsPackage> findByExternalId(String externalId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<FormsPackage> query =
        session.createNamedQuery(FormsPackage.NAMED_QUERY_FIND_BY_EXT_ID, FormsPackage.class);
    query.setParameter("extId", externalId);
    ImmutableList.Builder<FormsPackage> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  public List<FormsPackage> findByExternalIdAndStatus(String externalId, String status) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<FormsPackage> query =
        session.createNamedQuery(FormsPackage.NAMED_QUERY_FIND_BY_EXT_ID_AND_STATUS, FormsPackage.class);
    query.setParameter("extId", externalId);
    query.setParameter("status", status);
    ImmutableList.Builder<FormsPackage> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }


}
