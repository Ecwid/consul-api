package com.ecwid.consul.v1.connect.intentions;

import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.connect.intentions.model.IntentionResponse;
import java.util.List;

public interface IntentionsClient {

    Response<Boolean> createIntention(IntentionUpsertRequest request, String token);

    Response<Boolean> updateIntention(IntentionUpsertRequest request, String token);

    Response<List<IntentionResponse>> listIntentions(IntentionListRequest request, String token);

    Response<Boolean> deleteIntention(IntentionDeleteRequest request, String token);

}
