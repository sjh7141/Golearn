package com.golearn.repository;

import com.golearn.model.Member;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
