package datastorage;



public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    public JDConnectDAO createJdbcDAO(){return new JDConnectDAO(ConnectionBuilder.getConnection());}

    public CaregiverDAO createCaregiverDAO() {
        return new CaregiverDAO(ConnectionBuilder.getConnection());
    }
}