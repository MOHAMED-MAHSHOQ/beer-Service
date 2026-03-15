    package com.code.lambok.controller;

    import com.code.lambok.model.Beer;
    import com.code.lambok.services.BeerService;
    import com.code.lambok.services.BeerServiceImpl;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.ArgumentCaptor;
    import org.mockito.Captor;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
    import org.springframework.http.MediaType;
    import org.springframework.test.context.bean.override.mockito.MockitoBean;
    import org.springframework.test.web.servlet.MockMvc;
    import tools.jackson.databind.ObjectMapper;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;
    import java.util.UUID;

    import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
    import static org.hamcrest.core.Is.is;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.BDDMockito.given;
    import static org.mockito.Mockito.*;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @WebMvcTest
    @ExtendWith(MockitoExtension.class)
    public class BeerControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockitoBean
        private BeerService beerService;

        BeerServiceImpl beerServiceimpl;
        @Autowired
        private ObjectMapper objectMapper;

        @Captor
        ArgumentCaptor<UUID> uuidArgumentCaptor;

        @Captor
        ArgumentCaptor<Beer> beerArgumentCaptor;

        @BeforeEach
        void setUp(){
            beerServiceimpl = new BeerServiceImpl();
        }

        @Test
        void testCreateNewBeer() throws Exception {
            Beer beer = beerServiceimpl.listBeers().getFirst();
            beer.setVersion(null);
            beer.setId(null);

            given(beerService.saveNewBeer(any(Beer.class))).willReturn(beerServiceimpl.listBeers().get(1));

            mockMvc.perform(post(BeerController.BEER_PATH).accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beer)))
                    .andExpect(status().isCreated())
                    .andExpect(header().exists("Location"));
        }

        @Test
        void getBeerByIdNotFound() throws Exception {

            given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());

            mockMvc.perform(get(BeerController.BEER_PATH_ID,UUID.randomUUID()))
                    .andExpect(status().isNotFound());
        }

        @Test
        void getBeerById() throws Exception {
            Beer testBeer = beerServiceimpl.listBeers().getFirst();
            given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.of(testBeer));
            mockMvc.perform(get(BeerController.BEER_PATH + "/"+ testBeer.getId()).accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id",is(testBeer.getId().toString())))
                    .andExpect(jsonPath("$.beerName",is(testBeer.getBeerName())));
        }

        @Test
        void testListBeers() throws Exception {
            given(beerService.listBeers()).willReturn(beerServiceimpl.listBeers());

            mockMvc.perform(get(BeerController.BEER_PATH).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.length()",is(3)));
        }

        @Test
        void testUpdateBeer() throws Exception {
            Beer beer = beerServiceimpl.listBeers().getFirst();

            mockMvc.perform(put(BeerController.BEER_PATH_ID ,beer.getId()).accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beer)))
                    .andExpect(status().isNoContent());

            verify(beerService).updateBeerById(any(UUID.class),any(Beer.class));
        }

        @Test
        void testdeleteBeer() throws Exception {
            Beer beer = beerServiceimpl.listBeers().getFirst();

            mockMvc.perform(delete(BeerController.BEER_PATH_ID,beer.getId())
                    .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isNoContent());
            verify(beerService).deleteById(uuidArgumentCaptor.capture());
            assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        }
        @Test
        void testPatchBeer() throws Exception {
            Beer beer = beerServiceimpl.listBeers().getFirst();

            Map<String, Object> beerMap = new HashMap<>();
            beerMap.put("beerName", "New Name");

            mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(beerMap)))
                    .andExpect(status().isNoContent());

            verify(beerService).patchBeerById(
                    uuidArgumentCaptor.capture(),
                    beerArgumentCaptor.capture()
            );

            assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
            assertThat(beerMap.get("beerName"))
                    .isEqualTo(beerArgumentCaptor.getValue().getBeerName());
        }

    }
