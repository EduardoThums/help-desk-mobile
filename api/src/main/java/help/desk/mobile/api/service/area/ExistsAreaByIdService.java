package help.desk.mobile.api.service.area;

import help.desk.mobile.api.repository.area.AreaRepository;
import org.springframework.stereotype.Service;

/**
 * @author eduardo.thums
 */
@Service
public class ExistsAreaByIdService {

	private AreaRepository areaRepository;

	public ExistsAreaByIdService(AreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	public boolean existsById(Long id) {
		return areaRepository.existsById(id);
	}
}
