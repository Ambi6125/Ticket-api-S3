package sem3.project.individual.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sem3.project.individual.business.CreateArtistFunctionality;
import sem3.project.individual.domain.artists.CreateArtistRequest;
import sem3.project.individual.domain.artists.CreateArtistResponse;

@RestController
@RequestMapping("/artists")
public class ArtistController
{
    private CreateArtistFunctionality createFunctionality;


    @PostMapping
    public ResponseEntity<CreateArtistResponse> createArtist(@RequestBody CreateArtistRequest request)
    {
        var response = createFunctionality.create(request);
        return ResponseEntity.ok(response);
    }

}
