package com.zwigato.service.user.dto;

import java.time.LocalDateTime;

public record RolesDTO(Long roleId, String roleName,
                      String roleDescription,
                      LocalDateTime createdDate,
                      LocalDateTime lastModifiedDate) {
}
