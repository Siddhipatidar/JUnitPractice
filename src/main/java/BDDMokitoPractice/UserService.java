package BDDMokitoPractice;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(int id) {
        if (!UserUtils.isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return userRepository.findUserById(id);
    }

    public void removeUser(int id) {
        userRepository.deleteUser(id);
    }

}
