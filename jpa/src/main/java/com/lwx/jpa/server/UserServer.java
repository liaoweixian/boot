package com.lwx.jpa.server;

import com.lwx.jpa.domain.User;
import com.lwx.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServer {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    /**
     * 分页查询
     * @return
     */
    public Page<User> getUserPage(Pageable page) {
        return userRepository.findAll(page);
    }

    public List<User> getUserListSpecification(User user) {
        Specification<User> userSpecification = new Specification<User>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate email = criteriaBuilder.like(root.get("email"), user.getEmail());
                Predicate nickName = criteriaBuilder.equal(root.get("nickName"), user.getNickName());

               /* Path job = root.get("job");
                Predicate name = criteriaBuilder.equal(job.get("name"), user.getJob().getName());*/

                Predicate predicate = criteriaBuilder.and(email, nickName);
                return predicate;
            }
        };
        ArrayList<User> users = new ArrayList<>();
        User user1 = userRepository.findOne(userSpecification).orElse(new User());
        users.add(user1);
        return users;
    }

}
