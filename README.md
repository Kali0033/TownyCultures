# TownyReligions (TownyCultures)

INTRODUCTION
- TownyReligions is a fork of TownyCultures which only varies in using religions instead of cultures and also with the exclusion of Culture Channel.
- TownyCultures is an add on plugin for Towny, which enables "cultures" on Towny servers.
 
FEATURES
- Each town has a 'religion' e.g. "Hinduism", "Christianity", "Islam", "Taoism" etc.
- Each town religion is displayed on the Town screen.
- Town religion is fluid and dynamic. 
  There is no central administrator for each religion.
  <br>A new religion can be created by a mayor, using: `/c set <culture>`.
  <br>The same command can be used to join an existing religion.
- Nations do not create or specify religion in this way.
  <br>Instead their religion is the sum of whichever cultures their component towns identify with, 
  <br>and/or whichever religion(s) they might claim to be affiliated with.
- Religion can be shown on your dynmap' town popups.
  <br>Add %culture% in your Dynmap-Towny config's InfoWindow.
- Religion can be shown using the placeholder: %townycultures_culture%

COMMANDS

    PLAYER
    - `/religion set religion` - As a mayor, have the town create or join a religion
    
    ADMIN
    - `/ca reload` - Reload the plugin configs & language files
    - '/ca alltowns set religion <religion> - Set a culture for all towns
    - '/ca town <town> set religion <religion> - Set a culture for 1 town
      
INSTALLATION STEPS
1. Compile Jar file from source.
2. Drop the jar file into your normal plugins folder.
3. Stop your server.
4. Start your server.
5. Edit your townyperms.yml file in the Towny/settings folder, 
   and add the following permission for mayors (& other ranks if appropriate):
   `- townycultures.set_town_culture`.
6. Run the command `/ta reload townyperms`.   
7. That's it.
