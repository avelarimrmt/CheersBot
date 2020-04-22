import bl.SessionUtil;
import models.ProfessionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SqlRequests {

    public static List<ProfessionEntity> findProfession() {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();
        Query query = session.createNativeQuery("select * from profession").addEntity(ProfessionEntity.class);
        List<ProfessionEntity> entities = query.list();
        sessionUtil.closeTransactionSession();
        return entities;
    }
}