package com.github.morgenz.prizer.serivce;

import com.github.morgenz.prizer.domain.dto.ApplicationUserDto;
import com.github.morgenz.prizer.domain.entity.ApplicationUser;
import com.github.morgenz.prizer.domain.entity.Listing;
import com.github.morgenz.prizer.repository.RoleRepository;
import com.github.morgenz.prizer.repository.UserRepository;
import com.github.morgenz.prizer.security.PasswordEncoderConfig;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private ListingService listingService;
    private PasswordEncoderConfig passwordEncoderConfig;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    public ApplicationUser register(ApplicationUserDto applicationUserDto) {
        ApplicationUser applicationUser = convertToEntity(applicationUserDto);
        if (applicationUser.getRoles() == null) {
            applicationUser.setRoles(Collections.singletonList(roleRepository.findByRoleName("ROLE_USER")));
        }
        applicationUser.setPassword(passwordEncoderConfig.passwordEncoder().encode(applicationUser.getPassword()));
       return userRepository.save(applicationUser);
    }

    public List<ApplicationUser> getAllUsers() {
        return (List<ApplicationUser>) userRepository.findAll();
    }

    public void updateSteamInventoryListing(ApplicationUser applicationUser) {
        applicationUser.getItemLists().stream()
                .filter(Listing::isSteamInventoryList)
                .findAny()
                .ifPresentOrElse(listing -> listingService.updateSteamListing(applicationUser, listing), () -> listingService.addSteamListing(applicationUser));
    }

    private ApplicationUserDto convertToDto(ApplicationUser applicationUser){
        return modelMapper.map(applicationUser,ApplicationUserDto.class);
    }

    private ApplicationUser convertToEntity(ApplicationUserDto applicationUserDto){
        return modelMapper.map(applicationUserDto,ApplicationUser.class);
    }
}
