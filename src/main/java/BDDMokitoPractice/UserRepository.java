package BDDMokitoPractice;

public interface UserRepository {
    String findUserById(int id);
    void deleteUser(int id);
}
