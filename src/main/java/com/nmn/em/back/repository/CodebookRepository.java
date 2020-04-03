package com.nmn.em.back.repository;

public interface CodebookRepository<T> {

	default public T createNew() {
		throw new UnsupportedOperationException("Override createNew");
	}

}
