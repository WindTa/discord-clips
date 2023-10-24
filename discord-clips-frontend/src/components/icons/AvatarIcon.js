import Image from "react-bootstrap/Image"; 

function AvatarIcon({userId, avatarId}) {
    const avatarUrl = `https://cdn.discordapp.com/avatars/${userId}/${avatarId}.png`
    console.log(avatarUrl);
    return (
        <Image roundedCircle className=''
            src={avatarUrl}

        /> 
    );
}

export default AvatarIcon;
