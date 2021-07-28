package util;

import io.harness.cf.client.api.CfClient;
import io.harness.cf.client.api.Config;
import io.harness.cf.client.dto.Target;
import org.apache.log4j.Logger;

public class FeatureFlagManager {
  private final static Logger logger = Logger.getLogger(FeatureFlagManager.class);
  private static CfClient cfClient;
  static {
    logger.info("Initializing FeatureFlag");

    // SDK key is from this environment in UAT:
    // https://uat.harness.io/ng/#/account/FMs4-Pt6T7-YmTZa1RoXCA/cf/orgs/default/projects/harness360/feature-flags?activeEnvironment=demo360AppEnv
    String sdkKey = "e6b0ca0a-2cca-4408-b285-18dbc656b560";

    cfClient = new CfClient(sdkKey, Config.builder().build());

    logger.info(    cfClient.isInitialized());
  }

  public static boolean getFlagValue(String key, String user)
  {
    logger.info(    cfClient.isInitialized());

    return cfClient.boolVariation(key, Target.builder()
        .name(user)
        .identifier(user)
        .build(), false);
  }

}
