package matheus.github.task.enums;

public enum EnumRole {
     USER("USER"),
     ADMIN("ADMIN"),
     MANAGER("MANAGER");

     private final String role;

     EnumRole(String role) {
          this.role = role;
     }
}
