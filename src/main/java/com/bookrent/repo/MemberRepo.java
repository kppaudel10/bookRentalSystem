//package com.bookrent.repo;
//
//import com.bookrent.entity.Member;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.FluentQuery;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//public class MemberRepo implements JpaRepository<Member,Integer> {
//    @Override
//    public List<Member> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<Member> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Member> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<Member> findAllById(Iterable<Integer> integers) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//
//    }
//
//    @Override
//    public void delete(Member entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Integer> integers) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Member> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public <S extends Member> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Member> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Member> findById(Integer integer) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends Member> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Member> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<Member> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public Member getOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public Member getById(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public <S extends Member> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Member> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Member> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Member> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Member> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Member> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends Member, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//}
