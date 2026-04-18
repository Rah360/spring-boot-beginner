package come.rahullearnings.dto;

public class ErrorModel {
        public String message;
        public int errorCode;
        public long timestamp;
        public ErrorModel(String message,int errorCode){
            this.errorCode=errorCode;
            this.timestamp=System.currentTimeMillis();
            this.message=message;
        }
}
