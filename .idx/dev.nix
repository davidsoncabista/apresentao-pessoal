# To learn more about how to use Nix to configure your environment
# see: https://developers.google.com/idx/guides/customize-idx-env
{ pkgs, ... }: {
  # Which nixpkgs channel to use.
  channel = "stable-23.11"; # or "unstable"
  # Use https://search.nixos.org/packages to find packages
  packages = [
    pkgs.jdk17  # Changed from zulu17 to jdk17 for better compatibility
    pkgs.maven
  ];
  # Sets environment variables in the workspace
  env = {};
  idx = {
    # Search for the extensions you want on https://open-vsx.org/ and use "publisher.id"
    extensions = [
      "vscjava.vscode-java-pack"
      "google.gemini-cli-vscode-ide-companion"
    ];
    workspace = {
      # Runs when a workspace is first created with this `dev.nix` file
      onCreate = {
        install = "mvn clean install";
      };
      # The onStart command is removed as previews will handle the server start.
    };
    previews = {
      enable = true;
      previews = {
        # Name of the preview
        api = {
          # Command to start the Spring Boot server
          command = ["mvn", "spring-boot:run", "-Dserver.port=$PORT"];
          # Use "web" manager for HTTP services
          manager = "web";
          # The internal port to be mapped for the public preview
          port = 8080;
        };
      };
    };
  };
}