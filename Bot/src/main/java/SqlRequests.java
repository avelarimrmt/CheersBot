import bl.SessionUtil;
import models.ProfessionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class SqlRequests {
    private static ProfessionEntity prof;

    public static void findProfession(String profession) {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();

        Session session = sessionUtil.getSession();

        Query query = session.createNativeQuery("SELECT * FROM profession").addEntity(ProfessionEntity.class);
        List<ProfessionEntity> entities = query.list();
        int id = 0;
        //ProfessionEntity prof = new ProfessionEntity();
        for (int i = 0; i < entities.size(); i++) {
            ProfessionEntity professionEntity = entities.get(i);
            String profName = professionEntity.getProfName();
            if (profName.compareTo(profession) == 0) {
                prof = entities.get(i);
            }
        }
    }

    public static String findGenre(String genre) {
        Vector<String> toasts = new Vector<>();
        assert prof != null;
        for (int i = 0; i < prof.getToasts().size(); i++) {
            if (prof.getToasts().get(i).getGenre().getGenreName().equals(genre)) {
                toasts.add(prof.getToasts().get(i).getValueToast());
            }
        }

        int rand = toasts.size();
        int a = (int) (Math.random() * (rand));

        return toasts.get(a);
    }

   public static String getRandomGenre() {
        assert prof != null;

       int rand = prof.getToasts().size();
       int a = (int) (Math.random() * (rand));

       return (prof.getToasts().get(a).getValueToast());
    }
}

