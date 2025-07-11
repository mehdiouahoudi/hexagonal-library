package com.ouahoudi.library.application.usecase.book;

import java.util.List;

public record FindAuthorsByBookIdsCommand(List<Integer> bookIds) {}
