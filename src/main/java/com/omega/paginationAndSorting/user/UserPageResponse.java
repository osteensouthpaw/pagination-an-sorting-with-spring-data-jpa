package com.omega.paginationAndSorting.user;

import java.util.List;

public record UserPageResponse(
        List<User> users,
        int pageNumber,
        int pageSize,
        boolean sorted,
        int totalPages,
        long totalElements,
        boolean first,
        boolean last,
        long offset
) {
}
