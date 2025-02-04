package com.nikhillab.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikhillab.dto.AppConstants;
import com.nikhillab.dto.UserForm;
import com.nikhillab.entities.User;
import com.nikhillab.exception.ResourceNotFoundException;
import com.nikhillab.repo.UserRepo;
import com.nikhillab.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private EmailService emailService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public User saveUser(UserForm userForm) {

		User user = new User();
		user.setEmail(userForm.getEmail());
		user.setName(userForm.getName());
		user.setPhoneNumber(userForm.getPhone());
		user.setAbout(userForm.getAbout());
		user.setProfilePic("static/image/default_profile.png");
		user.setEnable(false);
		
//		 password encode
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

//		 set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

		logger.info(user.getProvider().toString());
//		String emailToken = UUID.randomUUID().toString();
//        user.setEmailToken(emailToken);
		User savedUser = userRepo.save(user);
//        String emailLink = Helper.getLinkForEmailVerificatiton(emailToken);
//        emailService.sendEmail(savedUser.getEmail(), "Verify Account : Smart  Contact Manager", emailLink);
		return savedUser;

	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public Optional<User> updateUser(UserForm user) {

//		User user2 = userRepo.findById(user.getUserId())
//				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
//		// update karenge user2 from user
//		user2.setName(user.getName());
//		user2.setEmail(user.getEmail());
//		user2.setPassword(user.getPassword());
//		user2.setAbout(user.getAbout());
//		user2.setPhoneNumber(user.getPhoneNumber());
//		user2.setProfilePic(user.getProfilePic());
////		user2.setEnabled(user.isEnabled());
//		user2.setEmailVerified(user.isEmailVerified());
//		user2.setPhoneVerified(user.isPhoneVerified());
//		user2.setProvider(user.getProvider());
//		user2.setProviderUserId(user.getProviderUserId());
//		// save the user in database
//		User save = userRepo.save(user2);
		return Optional.empty();

	}

	@Override
	public void deleteUser(Long id) {
		User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepo.delete(user2);

	}

	@Override
	public boolean isUserExist(Long userId) {
		User user2 = userRepo.findById(userId).orElse(null);
		return user2 != null ? true : false;
	}

	@Override
	public boolean isUserExistByEmail(String email) {
		User user = userRepo.findByEmail(email).orElse(null);
		return user != null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);

	}

}