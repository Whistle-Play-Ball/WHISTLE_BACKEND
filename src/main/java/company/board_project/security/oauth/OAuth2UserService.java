package company.board_project.security.oauth;
import company.board_project.constant.AuthProvider;
import company.board_project.security.utils.CustomAuthorityUtils;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        if(userRepository.findByEmail(attributes.getEmail()).isEmpty()) {
            log.info("### 소셜회원 신규가입 ###");
            saveUser(attributes.getEmail()
                    ,attributes.getName()
                    ,attributes.getProfileUrl()
                    ,attributes.getAuthProvider()
                    ,registrationId.toUpperCase());
        }

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private void saveUser(String email,
                          String nickname,
                          String profileUrl,
                          String profileKey,
                          String authProvider) {
        List<String> roles = authorityUtils.createRoles(email);
        User user = new User();

        user.setEmail(email);
        user.setName(nickname);
//        user.setProfileUrl(profileUrl);
//        user.setProfileKey(profileKey);
        user.setPassword(profileKey);
        user.setAuthProvider(AuthProvider.valueOf(authProvider));
        user.setRoles(roles);
        userRepository.save(user);
    }
}