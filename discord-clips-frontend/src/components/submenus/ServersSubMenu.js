import { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';

import { MenuItem, SubMenu } from 'react-pro-sidebar';

import AuthContext from '../../contexts/AuthProvider';

function ServersSubMenu({isActive, setActive}) {
    const { auth } = useContext(AuthContext);
	const [servers, setServers] = useState([]);

    const icon = <i className="bi bi-discord"></i>;

    useEffect(() => {
        setServers(auth.servers);
    }, []);

    return (
        <SubMenu 
            label="Servers"
            defaultOpen
            icon={icon}
        >
            {servers.map((server, idx) => {
                return (
                    <MenuItem 
                        active={isActive(`server-${idx}`)}
                        onClick={() => setActive(`server-${idx}`)}
                        key={idx} icon={icon}
                        component={ <Link to={`/servers/${server.id}`} /> }
                    >
                        {server.name}
                    </MenuItem>
                );
            })}
        </SubMenu>
    );
}
export default ServersSubMenu;


