package matheus.github.task.enums;

public enum EnumTaskStatus {
     PENDING("pending"),
     IN_PROGRESS("in_progress"),
     COMPLETED("completed");

     private String status;

     EnumTaskStatus(String status) {
          this.status = status;
     }

     public String getStatus() {
          return status;
     }
}
