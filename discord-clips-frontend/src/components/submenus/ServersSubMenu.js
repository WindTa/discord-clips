import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import { MenuItem, SubMenu } from 'react-pro-sidebar';

import { getServersByUser } from '../../services/server';

function ServersSubMenu({isActive, setActive}) {
	const [list, setList] = useState([]);

    const icon = <i className="bi bi-discord"></i>;

    useEffect(() => {
        setList(getServersByUser);
    }, []);

    return (
        <SubMenu 
            label="Servers"
            defaultOpen
            icon={icon}
        >
            {list.map((item, idx) => {
                return (
                    <MenuItem 
                        active={isActive(`server-${idx}`)}
                        onClick={() => setActive(`server-${idx}`)}
                        key={idx} icon={icon}
                        component={ <Link to={`/server`} /> }
                    >
                        {item}
                    </MenuItem>
                );
            })}
        </SubMenu>
    );
}
export default ServersSubMenu;


