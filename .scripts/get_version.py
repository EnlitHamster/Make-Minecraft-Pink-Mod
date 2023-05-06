import configparser

config = configparser.RawConfigParser()
config.read('gradle.properties')
version = config.get('MakeMinecraftPink', 'mod_version')
print(f'export MMP_VERSION="{version}"')
