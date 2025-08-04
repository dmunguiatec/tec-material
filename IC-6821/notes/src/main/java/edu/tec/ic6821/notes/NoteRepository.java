package edu.tec.ic6821.notes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notes", path = "notes")
public interface NoteRepository extends CrudRepository<Note, Long> {

}

