package com.accreditation.userservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

	private String token;

}
