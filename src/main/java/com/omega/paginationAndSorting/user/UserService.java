package com.omega.paginationAndSorting.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPageResponse findAll(int page, int size, String sortField, String sortDirection) {
        Sort sortOrder = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(Sort.Direction.ASC, sortField) :
                Sort.by(Sort.Direction.DESC, sortField);
        Pageable usersPage = PageRequest.of(page, size, sortOrder);
        Page<User> users = userRepository.findAll(usersPage);
        return new UserPageResponse(
                users.getContent(),
                users.getNumber(),
                users.getSize(),
                users.getSort().isSorted(),
                users.getTotalPages(),
                users.getTotalElements(),
                users.isFirst(),
                users.isLast(),
                usersPage.getOffset()
        );
    }
}
