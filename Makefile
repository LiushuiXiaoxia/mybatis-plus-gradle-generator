
pluginClean:
	sh z_script/PluginClean.sh

pluginBuild:
	sh z_script/PluginClean.sh
	sh z_script/PluginBuild.sh

pluginUpload:
	sh z_script/PluginClean.sh
	sh z_script/PluginUpload.sh
	sh z_script/PluginClean.sh