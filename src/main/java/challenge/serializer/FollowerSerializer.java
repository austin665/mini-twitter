package challenge.serializer;

import challenge.model.Follower;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FollowerSerializer extends JsonSerializer<Follower> {

    @Override
    public void serialize(Follower value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeNumberField("person_id", value.getPerson_id());
        gen.writeNumberField("follower_person_id", value.getFollower_person_id());
        gen.writeEndObject();
    }
}
