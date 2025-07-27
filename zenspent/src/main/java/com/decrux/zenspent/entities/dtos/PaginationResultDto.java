package com.decrux.zenspent.entities.dtos;


import java.util.List;

public record PaginationResultDto<E>(List<E> content,
                                     Integer totalPages,
                                     Long totalItems,
                                     Integer pageSize,
                                     Integer pageNumber,
                                     Boolean empty) {
}
