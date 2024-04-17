package matheus.github.task.enums;

public enum EnumTaskPriority {
     HIGH("high"),
     MEDIUM("medium"),
     LOW("low");

     private String priority;

     EnumTaskPriority(String priority) {
          this.priority = priority;
     }

     public String getPriority() {
          return priority;
     }
}
