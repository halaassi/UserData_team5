package edu.najah.cap.Export;

public class ExporterFactory {
    public Exporter createExporter(String dataType , String userName) {
        switch (dataType) {
             case "UserActivity":
               UserActivityExporter userActivityExporter =new UserActivityExporter();
               return userActivityExporter;
            case "UserProfile":
               UserProfileExporter userProfileExporter= new UserProfileExporter();
                return userProfileExporter;
            case "Payment":
                PaymentExporter paymentExporter =new PaymentExporter();
               return  paymentExporter ;
            case "Post":
               PostExporter postExporter= new PostExporter();
               return  postExporter;
            default:
                throw new IllegalArgumentException("Unsupported data type: " + dataType);
        }
    }
}