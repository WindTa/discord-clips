import Image from "react-bootstrap/Image"; 

function AvatarIcon({userId, avatarId}) {
    const avatarUrl = `https://cdn.discordapp.com/avatars/${userId}/${avatarId}.png`
    return (
        <Image roundedCircle className='avatar'
            src={avatarUrl}

        /> 
    );
}

export default AvatarIcon;
