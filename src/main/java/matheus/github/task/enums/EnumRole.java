package matheus.github.task.enums;

public enum EnumRole {
     USER("user"), ADMIN("admin"), MANAGER("manager");

     private final String role;

     EnumRole(String role) {
          this.role = role;
     }
}
