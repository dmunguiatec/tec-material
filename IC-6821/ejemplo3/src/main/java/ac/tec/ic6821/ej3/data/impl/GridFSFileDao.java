package ac.tec.ic6821.ej3.data.impl;

import ac.tec.ic6821.ej3.data.FileDao;
import ac.tec.ic6821.ej3.model.PersistedFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

@Repository("fileDao")
public class GridFSFileDao implements FileDao {

    @Autowired
    private GridFsOperations gridFsOperations;

    @Override
    public String create(PersistedFile image) {
        gridFsOperations.store(image.getInputStream(), image.getFilename());
        return image.getFilename();
    }

    @Override
    public PersistedFile findById(String filename) {
        GridFSDBFile file = gridFsOperations.findOne(Query.query(GridFsCriteria.whereFilename().is(filename)));
        return (file != null) ? new PersistedFile(file.getFilename(), file.getInputStream()) : null;
    }

    @Override
    public void update(PersistedFile image) {
        gridFsOperations.store(image.getInputStream(), image.getFilename());
    }

    @Override
    public void delete(String filename) {
        gridFsOperations.delete(Query.query(GridFsCriteria.whereFilename().is(filename)));
    }
}

