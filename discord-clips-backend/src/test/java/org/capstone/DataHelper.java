package org.capstone;

import org.capstone.models.Clip;
import org.capstone.models.DiscordServer;
import org.capstone.models.DiscordUser;
import org.capstone.models.Playlist;

public class DataHelper {

    public static DiscordUser makeDiscordUser(long discordUserId, String discordUsername) {
        DiscordUser discordUser = new DiscordUser();
        discordUser.setDiscordUserId(discordUserId);
        discordUser.setUsername(discordUsername);

        return discordUser;
    }
    public static DiscordServer makeDiscordServer(long discordServerId, String discordServerName) {
        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(discordServerId);
        discordServer.setServername(discordServerName);

        return discordServer;
    }

    public static Clip makeClip(
            int clipId, String clipName, String youtubeId,
            double startTime, double endTime,
            double volume, double playbackSpeed,
            long discordUserId) {
        Clip clip = new Clip();
        clip.setClipId(clipId);
        clip.setClipName(clipName);
        clip.setYoutubeId(youtubeId);
        clip.setStartTime(startTime);
        clip.setEndTime(endTime);
        clip.setVolume(volume);
        clip.setPlaybackSpeed(playbackSpeed);
        clip.setDiscordUserId(discordUserId);

        return clip;
    }

    public static Playlist makePlaylist(int playlistId, String playlistName, long discordUserId) {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setPlaylistName(playlistName);
        playlist.setDiscordUserId(discordUserId);

        return playlist;
    }
    public static DiscordUser makeWindTaUser() {
        return makeDiscordUser(221863292681977857L, "WindTa");
    }

    public static DiscordServer makeWindTaServer() {
        return makeDiscordServer(1161381438839607358L, "WindTa's server");
    }

    public static Clip makeWindTaClip() {
        return makeClip(1, "My Clip", "fSKQRDq3RkM",
                5, 10, 1, 1,
                221863292681977857L);
    }

    public static Playlist makeWindTasPlaylist() {
        return makePlaylist(1, "My Playlist", 221863292681977857L);
    }

}
