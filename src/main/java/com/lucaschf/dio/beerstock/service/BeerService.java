package com.lucaschf.dio.beerstock.service;

import com.lucaschf.dio.beerstock.dto.BeerDTO;
import com.lucaschf.dio.beerstock.entity.Beer;
import com.lucaschf.dio.beerstock.exception.BeerAlreadyRegisteredException;
import com.lucaschf.dio.beerstock.exception.BeerNotFoundException;
import com.lucaschf.dio.beerstock.exception.BeerStockExceededException;
import com.lucaschf.dio.beerstock.mapper.BeerMapper;
import com.lucaschf.dio.beerstock.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private static final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO.getName());
        var beer = beerMapper.toModel(beerDTO);
        var savedBeer = beerRepository.save(beer);

        return beerMapper.toDTO(savedBeer);
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        var foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name));

        return beerMapper.toDTO(foundBeer);
    }

    public List<BeerDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(name);

        if (optSavedBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(name);
        }
    }

    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        var beerToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();

        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(quantityAfterIncrement);
            var incrementedBeerStock = beerRepository.save(beerToIncrementStock);

            return beerMapper.toDTO(incrementedBeerStock);
        }

        throw new BeerStockExceededException(id, quantityToIncrement);
    }

    public BeerDTO decrement(Long id, int quantityToDecrement) throws BeerNotFoundException, BeerStockExceededException {
        var beerToDecrementStock = verifyIfExists(id);
        int beerStockAfterDecremented = beerToDecrementStock.getQuantity() - quantityToDecrement;

        if (beerStockAfterDecremented >= 0) {
            beerToDecrementStock.setQuantity(beerStockAfterDecremented);
            var decrementedBeerStock = beerRepository.save(beerToDecrementStock);

            return beerMapper.toDTO(decrementedBeerStock);
        }

        throw new BeerStockExceededException(id, quantityToDecrement);
    }
}