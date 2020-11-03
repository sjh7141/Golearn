package com.golearn.repository;

import com.golearn.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "select * from gl_video_tag vt left outer join gl_tag t on vt.tag_no=t.tag_no  where vt.vid_no=:vidNo", nativeQuery = true)
    List<Tag> findAllAndTagByVidNo(@Param("vidNo") int vidNo);
}
