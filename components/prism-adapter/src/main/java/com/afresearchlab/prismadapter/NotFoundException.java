package com.afresearchlab.prismadapter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "requirement not found")
public class NotFoundException extends RuntimeException {}
