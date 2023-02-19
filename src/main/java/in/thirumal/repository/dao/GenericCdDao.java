/**
 * 
 */
package in.thirumal.repository.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.thirumal.model.GenericCd;
import in.thirumal.repository.GenericCdRepository;

/**
 * @author Thirumal
 *
 */
@Repository
public class GenericCdDao extends GenericDao implements GenericCdRepository {

	Logger logger = LoggerFactory.getLogger(GenericCdDao.class);
	
	
	@Override
	public List<GenericCd> list(String tableName, Long localeCd) {
		String sql = getSql("GenericCd.listByTableName").replace("{TABLE_NAME}", tableName);
		System.out.println(sql);
		return jdbcTemplate.query(sql, genericCodeCdRowMapper, localeCd);
	}	
	
	RowMapper<GenericCd> genericCodeCdRowMapper = (rs, rowNum) -> {

		GenericCd genericCd = new GenericCd();

		genericCd.setCodeCd(rs.getObject(1) != null ? rs.getLong(1) : null);

		try {
			genericCd.setLocaleCd(rs.getObject("locale_cd") != null ? rs.getLong("locale_cd") : null);
		} catch (Exception e) {
			logger.debug("");
		}

		try {
			genericCd.setDescription(rs.getObject("description") != null ? rs.getString("description") : null);
		} catch (Exception e) {
			logger.debug("");
		}

		genericCd.setStartTime(rs.getObject("start_time") != null ? rs.getDate("start_time") : null);

		genericCd.setEndTime(rs.getObject("end_time") != null ? rs.getDate("end_time") : null);

		genericCd.setRowCreatedOn(rs.getObject("row_created_on") != null ? rs.getDate("row_created_on") : null);

		genericCd.setRowCreatedBy(rs.getObject("row_created_by") != null ? rs.getString("row_created_by") : null);

		genericCd.setRowUpdatedBy(rs.getObject("row_updated_by") != null ? rs.getString("row_updated_by") : null);

		genericCd.setUpdateInfo(rs.getObject("row_update_info") != null ? rs.getString("row_update_info") : null);

		return genericCd;
	};
}