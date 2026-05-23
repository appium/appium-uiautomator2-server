/*******************************************************************************
 * Copyright (c) 2005, 2011 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.internal.AttributeAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.ChildAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.DescendantAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.DescendantOrSelfAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.FollowingAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.FollowingSiblingAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.ForwardAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.SelfAxis;

/**
 * Class for Forward stepping support for Step operations.
 */
public class ForwardStep extends Step {
	/**
	 * Set internal value for NONE.
	 */
	public static final int NONE = 0;
	/**
	 * Set internal value for CHILD.
	 */
	public static final int CHILD = 1;
	/**
	 * Set internal value for DESCENDANT.
	 */
	public static final int DESCENDANT = 2;
	/**
	 * Set internal value for ATTRIBUTE.
	 */
	public static final int ATTRIBUTE = 3;
	/**
	 * Set internal value for SELF.
	 */
	public static final int SELF = 4;
	/**
	 * Set internal value for DESCENDANT_OR_SELF.
	 */
	public static final int DESCENDANT_OR_SELF = 5;
	/**
	 * Set internal value for FOLLOWING_SIBLING.
	 */
	public static final int FOLLOWING_SIBLING = 6;
	/**
	 * Set internal value for FOLLOWING.
	 */
	public static final int FOLLOWING = 7;
	/**
	 * Set internal value for NAMESPACE.
	 */
	public static final int NAMESPACE = 8;
	/**
	 * Set internal value for AT_SYM.
	 */
	public static final int AT_SYM = 9;

	private int _axis;

	// XXX: we should get rid of the int axis... and make only this the axis
	private ForwardAxis _iterator;

	// XXX: needs to be fixed
	private void update_iterator() {
		switch (_axis) {
		case NONE:
			if (node_test() instanceof AttributeTest)
				_iterator = new AttributeAxis();
			else
				_iterator = new ChildAxis();
			break;

		case CHILD:
			_iterator = new ChildAxis();
			break;

		case DESCENDANT:
			_iterator = new DescendantAxis();
			break;

		case FOLLOWING_SIBLING:
			_iterator = new FollowingSiblingAxis();
			break;

		case FOLLOWING:
			_iterator = new FollowingAxis();
			break;

		case AT_SYM:
		case ATTRIBUTE:
			_iterator = new AttributeAxis();
			break;

		case SELF:
			_iterator = new SelfAxis();
			break;

		case DESCENDANT_OR_SELF:
			_iterator = new DescendantOrSelfAxis();
			break;

		case NAMESPACE:
			throw new StaticError("XPST0010", "namespace axis not implemented");
			
		default:
			assert false;
			break;
		}
	}

	/**
	 * Constructor for ForwardStep.
	 * 
	 * @param axis
	 *            Axis number.
	 * @param node_test
	 *            Node test.
	 */
	public ForwardStep(int axis, NodeTest node_test) {
		super(node_test);

		_axis = axis;

		update_iterator();
	}

	/**
	 * Support for Visitor interface.
	 * 
	 * @return Result of Visitor operation.
	 */
	public Object accept(XPathVisitor v) {
		return v.visit(this);
	}

	/**
	 * Support for Axis interface.
	 * 
	 * @return Result of Axis operation.
	 */
	public int axis() {
		return _axis;
	}

	/**
	 * Set Axis to current.
	 * 
	 * @param axis
	 *            Axis to set.
	 */
	public void set_axis(int axis) {
		_axis = axis;
		update_iterator();
	}

	/**
	 * Support for Iterator interface.
	 * 
	 * @return Result of Iterator operation.
	 */
	public ForwardAxis iterator() {
		return _iterator;
	}
}
