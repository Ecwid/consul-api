package com.ecwid.consul.v1.connect;

import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.connect.model.CaConfigurationRequest;
import com.ecwid.consul.v1.connect.model.CaConfigurationResponse;
import com.ecwid.consul.v1.connect.model.CaRoots;

public interface ConnectClient {

	Response<CaRoots> connectListCaRoots();

	Response<CaConfigurationResponse> connectGetCaConfiguration();

	Response<CaConfigurationResponse> connectUpdateCaConfiguration(CaConfigurationRequest request);

}
