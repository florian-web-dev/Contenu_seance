package com.cda.contenu_seance.service;

import com.cda.contenu_seance.model.Intervenant;
import com.cda.contenu_seance.repositories.IntervenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class IntervenantDetailService implements UserDetailsService {

    @Autowired
    IntervenantRepository intervenantRepository;


//    @Bean
//    public PasswordEncoder delegatingPasswordEncoder() {
//        PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
//
//        DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder(
//                "bcrypt", encoders);
//        passworEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);
//
//        return passworEncoder;
//    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Intervenant coordinateur = intervenantRepository.findByEmail(username);
//        Intervenant formateur = intervenantRepository.findByEmail(username);

        Intervenant intervenant = intervenantRepository.findByEmail(username);


        if (null == intervenant ){
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }
//        if(null != coordinateur){
//            roles = new String[]{lowCaseRole, lowCaseRole2};
//        }else {
//            roles = new String[]{lowCaseRole};
//        }
        Set<GrantedAuthority> authorities = new HashSet<>();


        String lowCaseRole = intervenant.getClass().getSimpleName().toLowerCase();
//        String lowCaseRole2 = intervenant.getClass().getSimpleName().toLowerCase();

//        String[]roles= {lowCaseRole,lowCaseRole2};

        //array formateur coordinateur
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(lowCaseRole);
//        simpleGrantedAuthority.getAuthority();

        authorities.add(new SimpleGrantedAuthority(lowCaseRole));


//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pas = encoder.encode(intervenant.getMp());
//        System.out.println("PassWord :"+ pas);
        return new User(
                intervenant.getEmail(),
                intervenant.getMp(),
                authorities);
    }

}
