package com.videogameapp.cucumber.steps;

import com.videogameapp.utils.TestUtils;
import com.videogameapp.videoGameInfo.VideoGameSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

public class MyStepdefs {

    static String name=null;
    static String videoGameId=null;
    static ValidatableResponse response=null;
    @Steps
    VideoGameSteps videoGameSteps;

    @When("^User sends a get request to list endpoint, they must get back a valid status code 200$")
    public void userSendsAGetRequestToListEndpointTheyMustGetBackAValidStatusCode() {

        videoGameSteps.getAllVideoGame().statusCode(200);
    }

    @When("^User sends a get request to id \"([^\"]*)\" endpoint, they must get back a valid status code 200$")
    public void userSendsAGetRequestToIdEndpointTheyMustGetBackAValidStatusCode(String videoGameId) throws Throwable {

        videoGameSteps.getSingleVideoGame(Integer.parseInt(videoGameId)).log().all().statusCode(200);
    }

    @When("^I create a new video game by providing the information name\"([^\"]*)\",releaseDate\"([^\"]*)\",reviewScore\"([^\"]*)\",category\"([^\"]*)\",rating\"([^\"]*)\"$")
    public void iCreateANewVideoGameByProvidingTheInformationNameReleaseDateReviewScoreCategoryRating() {


    }

    @When("^I create a new video game by providing the information id\"([^\"]*)\" name\"([^\"]*)\",releaseDate\"([^\"]*)\",reviewScore\"([^\"]*)\",category\"([^\"]*)\",rating\"([^\"]*)\"$")
    public void iCreateANewVideoGameByProvidingTheInformationIdNameReleaseDateReviewScoreCategoryRating(String id,String _name, String releaseDate, String reviewScore, String category, String rating) {

        name=_name+TestUtils.getRandomValue();
        videoGameId=id+TestUtils.getRandomNumber();
        response=videoGameSteps.createVideoGame(Integer.parseInt(videoGameId),name,releaseDate,Integer.parseInt(reviewScore),category,rating);
    }

    @Then("^I verify that the video game with name \"([^\"]*)\" is created$")
    public void iVerifyThatTheVideoGameWithNameIsCreated(String _name)  {

        response.statusCode(200);
        HashMap<String,Object> videoGameInfo=videoGameSteps.getVideoGameInfoByName(name);
        assertThat(videoGameInfo,hasValue(name));
    }

    @When("^I update a new video game by providing the information id\"([^\"]*)\" name\"([^\"]*)\",releaseDate\"([^\"]*)\",reviewScore\"([^\"]*)\",category\"([^\"]*)\",rating\"([^\"]*)\"$")
    public void iUpdateANewVideoGameByProvidingTheInformationIdNameReleaseDateReviewScoreCategoryRating(String id,String _name, String releaseDate, String reviewScore, String category, String rating) {

        name=_name+TestUtils.getRandomValue();
        response=videoGameSteps.updateVideoGame(Integer.parseInt(id),name,releaseDate,Integer.parseInt(reviewScore),category,rating);

    }

    @Then("^I verify that the video game with name \"([^\"]*)\" is updated$")
    public void iVerifyThatTheVideoGameWithNameIsUpdated(String _name) {

        response.statusCode(200);
        HashMap<String,Object> videoGameInfo=videoGameSteps.getVideoGameInfoByName(name);
        assertThat(videoGameInfo,hasValue(name));
    }

    @When("^I delete a video game by providing the information id\"([^\"]*)\"$")
    public void iDeleteAVideoGameByProvidingTheInformationId(String id) {

      response=videoGameSteps.deleteVideoGameWithId(Integer.parseInt(id));
    }

    @Then("^I verify that the video game with id\"([^\"]*)\" is deleted$")
    public void iVerifyThatTheVideoGameWithIdIsDeleted(String id) {

        response.statusCode(200);
        videoGameSteps.getSingleVideoGame(Integer.parseInt(id)).statusCode(500);

    }
}
