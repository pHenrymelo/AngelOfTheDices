package dev.kaiserInc.AngelOfTheDices.table;

import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import dev.kaiserInc.AngelOfTheDices.table.dto.GameTableCreateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.table.dto.GameTableMapper;
import dev.kaiserInc.AngelOfTheDices.user.User;
import dev.kaiserInc.AngelOfTheDices.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameTableService {

    private final GameTablesRepository gameTablesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public GameTableService(GameTablesRepository gameTablesRepository, UsersRepository usersRepository) {
        this.gameTablesRepository = gameTablesRepository;
        this.usersRepository = usersRepository;
    }

    public GameTable createTable(GameTableCreateRequestDTO dto, UUID userId) {

        User gm = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        GameTable newTable = GameTableMapper.toEntity(dto);
        newTable.setGm(gm);

        return gameTablesRepository.save(newTable);
    }

    public List<GameTable> findAllTables() {
        return gameTablesRepository.findAll();
    }
}
